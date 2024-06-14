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