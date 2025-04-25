com.tddrestapi
├── domain
│   └── user
│       ├── User.kt
│       └── UserRepository.kt
│
├── application
│   └── user
│       ├── UserService.kt
│       └── UserServiceImpl.kt
│
├── presentation
│   └── user
│       ├── UserController.kt
│       └── dto
│           ├── UserSignupRequest.kt
│           └── UserResponse.kt
│
├── infrastructure
│   └── config
│       └── SwaggerConfig.kt (옵션)
│
└── support
├── exception
│   ├── GlobalExceptionHandler.kt
│   └── UserAlreadyExistsException.kt
└── util
└── PasswordHasher.kt
