# 📊 Quant Risk AI (Crypto Version)

> 비트코인, 이더리움 등 암호화폐 시장에서 매크로·기술·온체인·감성 데이터를 통합 분석하여 **리스크 기반 투자 전략**을 제시하는 AI 시스템

---

## 🧱 시스템 구성도 (Crypto Quant 중심)

```
                 ┌────────────┐
                 │   사용자     │
                 └────┬───────┘
                      │
            ┌─────────▼──────────┐
            │     React UI       │ ← 수익률/리스크 시각화, 전략 백테스트, 알림 설정
            └────────┬───────────┘
                     ▼
     ┌──────────────────────────────┐
     │     Spring Boot API 서버      │ ← 요청 처리, 전략 실행 관리, 스케줄링
     └────────┬──────────────┬──────┘
              ▼              ▼
   ┌────────────────┐   ┌──────────────┐
   │   Python Quant │   │ MongoDB      │
   │   전략 엔진      │   │ 결과 저장      │
   └────┬───────────┘   └──────────────┘
        ▼
    ┌─────────────┐
    │ Kafka       │ ← 실시간 가격, 온체인, 감성 데이터 수집 스트림
    └─────────────┘
```

---

## 🔍 핵심 기능

| 기능 | 설명 |
|------|------|
| ✅ 전략 구성 | RSI, Bollinger Bands, 이동평균, 거래량, 트렌드 추종 등 rule-based 전략 |
| ✅ 리스크 분석 | Sharpe ratio, max drawdown, 변동성, 손절/익절 위치 추천 |
| ✅ 감성 분석 | 트위터/Fear&Greed Index, 뉴스 헤드라인 감정 점수화 |
| ✅ 온체인 분석 | 지갑 수, 거래량, 스테이킹 물량, whale activity 등 추출 |
| ✅ 실시간 경고 | 기준 초과 변동 시 알림 (Telegram 연동) |
| ✅ 백테스트 | 전략별 연 수익률, 리스크 대비 수익률 비교 시각화 |
| ✅ 포트폴리오 시뮬레이션 | BTC/ETH 비중 조정에 따른 리스크 관리 효율화 실험 |

---

## 🧱 시스템 아키텍처 (경량화 MVP 버전)

```
 사용자 ──> Netlify (React UI)

   EC2 t2.micro (Spring Boot API)
     ├── 실시간 Binance 가격 수집 (1분 단위)
     ├── 조건 만족 시 전략 분석 요청 (Python Flask 호출)
     └── Telegram 알림 + MongoDB Atlas 저장

   Flask (Python 전략 분석 API) → 로컬 or EC2 Flask 포트
   MongoDB Atlas (무료 외부 DB)
```

---

## 🎯 현실적 MVP 기능 정의

| 우선순위 | 기능 | 설명 |
|----------|------|------|
| ✅ 1 | 실시간 가격 수집 | Binance API로 1분마다 가격 가져오기 |
| ✅ 2 | 전략 조건 매칭 | (ex: RSI > 70 + BB 상단 돌파) |
| ✅ 3 | Telegram 알림 | 전략 조건 충족 시 푸시 전송 |
| ✅ 4 | MongoDB 저장 | 결과 및 경고 이력 저장 |
| 🕓 5 | React UI (정적) | 전략 결과 시각화 (Netlify 배포) |
| 🕓 6 | Python 전략 API | Pandas 기반 단순 전략 실행 API |
| ❌ 7 | 감성/온체인 분석 | 트위터, Santiment API 등 (후순위) |

---

## 🛠 기술 스택


# 🔧 Quant Risk AI - 기술 스택 상세 설명

> 암호화폐 리스크 기반 투자 전략 시스템 (MVP 기준)

---

## 📦 기술 스택 정리

| 계층 | 기술 | 설명 및 선택 이유 |
|------|------|------------------|
| **프론트엔드** | `React` | 컴포넌트 기반 UI, 상태 관리 용이. 추후 실시간 차트 연동에 적합 |
| | `Tailwind CSS` | 빠른 스타일링, 반응형 대응. 디자인 시스템 구축 용이 |
| | `Chart.js` | 전략 수익률/리스크 등 시각화용, 간편한 라인/도넛 차트 구현 |
| **백엔드** | `Spring Boot` | 안정적인 REST API 제공, 스케줄링과 보안 설정에 강점 |
| | `RestTemplate/WebClient` | Flask 전략 서버와의 연동용 HTTP 클라이언트 |
| | `Spring Scheduler` | 매분 가격 수집 및 전략 조건 평가 스케줄링에 사용 |
| | `Telegram Bot API` | 경고/알림 자동 전송. 추후 포맷 템플릿화 계획 |
| **전략 분석** | `Python` | 수치 계산, 전략 테스트에 적합한 생태계 |
| | `Pandas` | 전략 조건 계산, 데이터 전처리 |
| | `Backtrader` (선택) | 백테스트 및 전략 비교를 위한 프레임워크 (후속 적용 예정) |
| | `Flask` | 경량 REST API 서버로 Spring과 연동. 전략 결과 JSON 반환 |
| **실시간 데이터** | `Binance API` | 실시간 1분 봉 가격 데이터 제공 (REST 기반) |
| **데이터베이스** | `MongoDB Atlas` | NoSQL 기반. 시계열 전략 결과 저장에 유연. 무료 Tier로 MVP 최적 |
| **메시지 큐 (확장)** | `Kafka` | 향후 실시간 감성·온체인 이벤트 스트리밍용 (확장 단계) |
| **배포** | `EC2 (t2.micro)` | Flask + Spring Boot 백엔드 배포. 저비용 운영 가능 |
| | `Netlify` | 정적 React 프론트 배포. CI/CD 연동 용이 |
| **환경 변수 관리** | `.env + System.getenv()` | API Key, Bot Token 등 민감 정보 관리 |

---

## 🔐 보안 권장사항
- `.env` 파일 또는 환경변수로 API Key/Token 분리
- Spring Security 인증 모듈 고려 (관리자 페이지 대비)
- JWT 인증은 고도화 단계에서 적용

---

## 🐳 도커화 제안 (선택)
- 각 서버에 `Dockerfile` 작성
- `docker-compose` 로 통합 개발 환경 구성
- 향후 ECS + ECR + RDS 로 확장성 확보

---

## 🧪 테스트/모니터링 제안
- Postman으로 API 시나리오 자동 테스트
- Spring Boot Actuator로 서버 헬스 체크
- Netlify CI/CD 연동으로 자동 배포 구성


---

## ✅ 개발 단계 및 체크리스트

### 🧩 1단계: 백엔드 뼈대 + 경고 시스템
- [ ] Spring Boot 서버 기본 구성 (API + Scheduler)
- [ ] Binance 가격 수집기 (RestTemplate 또는 WebClient)
- [ ] 조건부 경고 트리거 로직 구현 (ex. RSI > 70)
- [ ] Telegram 메시지 전송 기능 구현
- [ ] MongoDB Atlas 연동 (Spring Data)

### 🧩 2단계: 전략 분석 엔진 연동
- [ ] Python Flask 서버 작성 (`/run-strategy`)
- [ ] Spring → Flask REST 연동 (`RestTemplate`)
- [ ] 전략 결과 JSON 저장 및 파싱

### 🧩 3단계: 프론트엔드 시각화 + 배포
- [ ] React 기반 전략 시각화 UI (Tailwind + Chart.js)
- [ ] MongoDB 데이터 조회 API 추가 (Spring)
- [ ] 정적 사이트 빌드 후 Netlify 업로드

### 🧩 4단계 (선택): 고도화 및 확장
- [ ] 감성 데이터 (뉴스 헤드라인, Fear & Greed Index)
- [ ] 온체인 지표 (지갑 수, 거래량 등)
- [ ] Kafka or MQ 기반 실시간 이벤트 처리
