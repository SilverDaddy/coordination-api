### ✅ 1. 빌드 환경

| 항목              | 버전                |
|-----------------|-------------------|
| **JDK**         | 21                |
| **Spring Boot** | 3.4.4             |
| **Kotlin**      | 1.9.25            |
| **Gradle**      | 8.13              |
| **Build Tool**  | Gradle Kotlin DSL |

### ✅ 2. 테스트, 빌드 및 실행

#### 📦 테스트

```bash
./gradlew test
```

- `build/reports/tests/test/index.html` → HTML 리포트로 테스트 결과 확인 가능

#### 📦 빌드

```bash
./gradlew clean build
```

#### 📦 실행

```bash
./gradlew bootRun
```

### ✅ 3. Swagger API 문서

👉 [Swagger API 문서](http://localhost:8080/swagger-ui/index.html)

### 💼 관리자 기능

#### 📌 1. 상품 관리 [`/api/admin/v1/products`]

- **상품 등록**
    - `POST /api/admin/v1/products`
    - 새로운 상품을 등록합니다.

- **상품 수정**
    - `PUT /api/admin/v1/products/{id}`
    - 기존 상품 정보를 수정합니다.
    - `id`: 수정할 상품의 ID

- **상품 삭제 (Soft Delete)**
    - `DELETE /api/admin/v1/products/{id}`
    - 상품을 삭제 상태로 처리합니다. 실제 데이터베이스 삭제는 아닙니다.

- **상품 목록 조회**
    - `GET /api/admin/v1/products`
    - 전체 상품 리스트를 조회합니다.

---

#### 📌 2. 브랜드 관리 [`/api/admin/v1/brands`]

- **브랜드 등록**
    - `POST /api/admin/v1/brands`
    - 신규 브랜드를 등록합니다.

- **브랜드 수정**
    - `PUT /api/admin/v1/brands/{id}`
    - 기존 브랜드 정보를 수정합니다.
    - `id`: 수정할 브랜드의 ID

- **브랜드 삭제 (Soft Delete)**
    - `DELETE /api/admin/v1/brands/{id}`
    - 브랜드를 삭제 상태로 처리합니다.

- **브랜드 목록 조회**
    - `GET /api/admin/v1/brands`
    - 전체 브랜드 리스트를 조회합니다.

---

#### 📌 3. 카테고리 관리 [`/api/admin/v1/categories`]

- **카테고리 등록**
    - `POST /api/admin/v1/categories`
    - 신규 카테고리를 등록합니다.

- **카테고리 수정**
    - `PUT /api/admin/v1/categories/{id}`
    - 기존 카테고리 정보를 수정합니다.
    - `id`: 수정할 카테고리의 ID

- **카테고리 삭제 (Soft Delete)**
    - `DELETE /api/admin/v1/categories/{id}`
    - 카테고리를 삭제 상태로 처리합니다.

- **카테고리 목록 조회**
    - `GET /api/admin/v1/categories`
    - 전체 카테고리 리스트를 조회합니다.

### 🧍‍ 사용자 기능 (코디네이션 서비스)

#### 📌 1. 카테고리 요약 조회 [`/api/client/v1/coordination/category-summary`]

- `GET /api/client/v1/coordination/category-summary`
- 각 카테고리별 최저가 브랜드 및 가격을 요약 조회합니다.
- 전체 카테고리에서 최저가 상품들의 총합도 함께 제공됩니다.

#### 📌 2. 단일 브랜드 최저가 코디 추천 [`/api/client/v1/coordination/cheapest-brand`]

- `GET /api/client/v1/coordination/cheapest-brand`
- 단일 브랜드가 보유한 상품들 중, 전 카테고리를 모두 포함하고 있는 최저가 브랜드를 추천합니다.
- 각 카테고리별 해당 브랜드의 최저 상품 가격과 총합을 제공합니다.

#### 📌 3. 카테고리별 최고/최저가 브랜드 조회 [`/api/client/v1/coordination/category-extreme`]

- `GET /api/client/v1/coordination/category-extreme?category={카테고리명}`
- 특정 카테고리에서 최저가 및 최고가 브랜드와 해당 상품 가격을 조회합니다.
- 예: `category=상의`, `category=바지` 등
