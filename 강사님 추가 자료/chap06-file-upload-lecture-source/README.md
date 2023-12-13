# file upload

- 작성된 버전은 bulid된 폴더에 파일을 다운로드 받는 방식으로 스프링 서버 로드 후 이미지를 실시간으로 볼 수 없는 문제를 해결하였다.
- 아래의 경로에 파일 업로드가 진행된다. ![img.png](img.png)
- 위 작업을 위해서는 application.propertis 파일에 <mark><strong> spring.mvc.static-path-pattern=/static/** </strong></mark> 가 추가되어야 한다.