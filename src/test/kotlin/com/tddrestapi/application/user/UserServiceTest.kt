package com.tddrestapi.application.user

import io.kotest.core.spec.style.StringSpec
import io.mockk.mockk


class UserServiceTest : StringSpec({

    val userRepository = mockk<UserRepository>()
    val userService = UserServiceImpl(userRepository)

    "회원 가입 성공" {
        // given
        val email = "test@example.com"
        val name = "테스트"
        val password = "1234"
        val newUser = User(email, name, password)

        every { userRepository.findByEmail(email) } returns null
        every { userRepository.save(any()) } returns newUser

        // when
        val result = userService.register(email, name, password)

        // then
        result.email shouldBe email
        result.name shouldBe name
        verify { userRepository.save(any()) }
    }

    "이메일이 중복되면 예외 발생" {
        // given
        val email = "test@example.com"
        val name = "테스트"
        val password = "1234"
        val existingUser = User(email, name, password)

        every { userRepository.findByEmail(email) } returns existingUser

        // when
        val exception = shouldThrow<UserAlreadyExistsException> {
            userService.register(email, name, password)
        }

        // then
        exception.message shouldBe "이미 존재하는 이메일입니다: $email"
        verify(exactly = 0) { userRepository.save(any()) }
    }

    "이메일로 유저 조회 성공" {
        // given
        val email = "test@example.com"
        val user = User(email, "테스트", "1234")

        every { userRepository.findByEmail(email) } returns user

        // when
        val result = userService.getByEmail(email)

        // then
        result.email shouldBe email
        result.name shouldBe "테스트"
        verify { userRepository.findByEmail(email) }
    }

    "존재하지 않는 이메일 조회 시 예외 발생" {
        // given
        val email = "unknown@example.com"
        every { userRepository.findByEmail(email) } returns null

        // when
        val exception = shouldThrow<UserNotFoundException> {
            userService.getByEmail(email)
        }

        // then
        exception.message shouldBe "사용자를 찾을 수 없습니다: $email"
    }
})
