import http from 'k6/http';
import { check, sleep } from 'k6';
import { randomIntBetween, randomItem } from "https://jslib.k6.io/k6-utils/1.2.0/index.js";

export let options = {
    scenarios: {
        reservation_scenario: {
            vus: 100,
            exec: 'reservation_scenario',
            executor: 'per-vu-iterations',
            iterations: 100
        }
    }
};

export function reservation_scenario() {
    let userId = randomIntBetween(1, 100);
    let concertDetailId = randomIntBetween(1, 500000);
    let seatId = randomIntBetween(1, 35);

    // 콘서트 조회
    searchConcert(userId, concertDetailId);

    sleep(5);
    // 좌석 조회
    searchSeat(concertDetailId);

    // 예약
    // 활성화 큐가 꽉차지 않았다면 토큰 활성화
    let paymentInfo = reservation(userId, concertDetailId, seatId);

    sleep(10);

    // 결제
    payment(userId, concertDetailId, concertDetailId);
}


function charge(userId) {
    let chargePoint = randomIntBetween(10, 50) * 100;

    let chargeRes = http.post(
        `http://host.docker.internal:8080/balance/charge`, JSON.stringify({
            userId: userId,
            balance: chargePoint
        }),{
            headers: {'Content-Type': 'application/json'},
            tags: { name : 'charge'}
        }
    )

    check(chargeRes, {'is status 200': (r) => r.status === 200});
}

function searchConcert(concertDetailId, userId) {
    let searchRes = http.get(
        `http://host.docker.internal:8080/concerts/detail/${concertDetailId}`,
        {
            headers: {'Content-Type': 'application/json'},
            tags: { name : 'search-concert'}
        }
    )
    check(searchRes, {'is status 200': (r) => r.status === 200});
}

function reservation(userId, concertDetailId, seatId) {
    let reservationRes = http.post(
        `http://host.docker.internal:8080/reservation`, JSON.stringify({
            userId: userId,
            concertDetailId: concertDetailId,
            seatId: seatId
        }),{
            headers: {
                'Content-Type': 'application/json',
            },
            tags: { name : 'reservation'}
        }
    )
    check(reservationRes, {'is status 200': (r) => r.status === 200});

    return {
        paymentId: randomItem(reservationRes.json())['paymentInfo']['paymentId'],
        point: randomItem(reservationRes.json())['paymentInfo']['point']
    }
}

function searchSeat(concertDetailId) {
    let searchSeatRes = http.get(
        `http://host.docker.internal:8080/concerts/${concertDetailId}/seat`,
        {
        headers: {
            'Content-Type': 'application/json'
        },
            tags: {name : 'search-seat'}
        }
    )

    check(searchSeatRes, {'is status 200': (r) => r.status === 200});
}

function payment(userId, paymentId, price) {
    let paymentRes = http.post(
        `http://host.docker.internal:8080/payment`, JSON.stringify({
            userId: userId,
            paymentId: paymentId,
            point: price
        }),{
            headers: {
                'Content-Type': 'application/json',
                'Authorization': userId.toString()
            },
            tags: { name : 'payment'}
        }
    )
    check(paymentRes, {'is status 200': (r) => r.status === 200});
}




/*export let options = {
    vus: 300, // 가상 사용자 수
    duration: '10m', // 테스트 지속 시간
};

export default function () {
    // 랜덤 데이터 생성
    const id = randomIntBetween(1, 1000000);
    const name = randomString(10);
    const age = randomIntBetween(1, 99);
    const address = `Random Street ${randomIntBetween(1, 1000)}`;

    // JSON 바디 구성
    const body = JSON.stringify({
        id: id,
        name: name,
        age: age,
        address: address
    });

    // HTTP POST 요청 설정
    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    // POST 요청 보내기
    let response = http.post('http://localhost:8080/api/people', body, params);

    // 응답 확인
    check(response, {
        'is status 200': (r) => r.status === 200,
    });
}*/
