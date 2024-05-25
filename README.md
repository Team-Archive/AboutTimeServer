# AboutTimeServer

## Tech stack
- Jdk 17, Java, Gradle
- Spring Framework(v6.1.6)
  - Spring Boot(v3.2.5)
  - Spring Data Jpa
  - Spring Security
  - ...

## Git Branch Strategy
[깃 플로우](https://techblog.woowahan.com/2553/)와 [깃허브 플로우](https://docs.github.com/ko/get-started/using-github/github-flow)를 적절히 사용합니다.

## Commit Message
커밋메시지앞에 주제를 붙혀주세요
- feat: 새로운 기능 추가
- fix: 버그 수정
- docs: 문서 수정
- style: 코드 스타일 변경 (코드 포매팅, 세미콜론 누락 등)
- design: 사용자 UI 디자인 변경
- test: 테스트 코드, 리팩토링 (Test Code)
- refactor: 리팩토링 (Production Code)
- build: 빌드 파일 수정
- ci: CI 설정 파일 수정
- perf: 성능 개선
- chore: 자잘한 수정이나 빌드 업데이트
- rename: 파일 혹은 폴더명을 수정만 한 경우
- remove: 파일을 삭제만 한 경우
> ex) feat: 좋아요 버튼을 누를 때 햅틱 피드백 추가