## 기술 블로그 

Java와 Spring FrameWork로 개발하고 있는 저의 기술 블로그 REST API 입니다 <br/>
추후 프론트 개발도 하여 기술 블로그를 완성 하려고 합니다

로그인 회원가입 기능은 Spring Security를 사용했습니다

<a href="https://github.com/tuioe5679/blogReact">프론트 개발<a/> <br>

### 기능 (API)

- 게시물 관련 API 
  - 모든 게시글 조회 /boards (GET)
  - 해당 게시글 조회 /board/{id} (GET)
  - 게시글 작성 /board (POST)
  - 해당 게시글 수정 /board/{id} (PUT)
  - 해당 게시글 삭제 /board/{id} (DELETE)
  - 모든 게시글 삭제 /boards (DELETE)
  <br><br>
- 댓글 관련 API 
  - 모든 댓글 조회 /comments (GET)
  - 댓글 작성 /comment (POST)
  - 해당 댓글 수정 /comment/{id} (PUT)
  - 해당 댓글 삭제 /comment/{id} (DELETE)
  <br><br>
- 관리자 관련 API 
  - 모든 유저의 정보를 조회 /members (GET)
  - 해당 유저의 정보를 조회 /member/{id} (GET)
  - 해당 유저의 정보를 삭제 /member/{id} (GET)

