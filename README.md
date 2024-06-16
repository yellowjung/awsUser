# AWS User Get Project

이 프로젝트는 AWS IAM 액세스 키 관리를 자동화하는 Spring Boot 애플리케이션입니다.

### 사용 목적
> Access Key Pair를 생성한지 N시간을 초과하는 IAM User의
> User ID와 Access Key ID 반환하는게 목적입니다.


## 설치 및 실행

### 로컬 환경에서 실행
0. AWS CLI가 사전에 준비되어 Access key ID와 Secret access key가 config로 등록되어있어야합니다.
``` bash
https://docs.aws.amazon.com/ko_kr/cli/latest/userguide/getting-started-install.html
참고하세요
```
1. 프로젝트를 클론합니다.
```bash
git clone https://github.com/yellowjung/awsUser
cd awsUser
```
2. Maven을 사용하여 빌드합니다.
```bash
mvn clean package
```
3. 생성된 JAR 파일을 실행합니다.
```bash
java -jar target/.jar
```

## 사용 예제

### IAM 사용자의 오래된 액세스 키 조회

Access Key Pair 를 생성한지 N시간을 초과하는 IAM
User의 User ID와 Access Key ID를 조회하는 예제입니다.

```bash
GET /old-access-keys?hours=24
```

## DockerFile 사용 방법

### Docker build
docker 혹은 podman이 설치되어있는 환경에서 아래의 명령을 실행합니다.
명령어 실행 위치는 root dir입니다.
```bash
docker
docker build -t {image_name}:{tag} .

podman
podman build -t {image_name}:{tag} .
```

### Docker run
생성한 이미지를 수행시킵니다.
이 때 미리 발급 받은 AWS_ACCESS_KEY_ID, 및 AWS_SECRET_ACCESS_KEY를 
환경변수로 입력합니다.
```bash
docker run -e AWS_ACCESS_KEY_ID={AWS_ACCESS_KEY_ID} \
           -e AWS_SECRET_ACCESS_KEY={AWS_SECRET_ACCESS_KEY} \
           -p 8080:8080 {image_name}:{tag}
```

