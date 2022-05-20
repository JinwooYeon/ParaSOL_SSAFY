# Core 서버 빌드 방법
```
# application.properties 복사 후
mv ./core
gradle :application:core-application:build
```

# Main MSA 서버 빌드 방법
```
# application.properties 복사 후
# 각 서버로 이동
mv ./main-*
gradle build
```

# BaaS 서버 빌드 방법
```
# application.properties 복사 후
# 각 서버로 이동
mv ./service
gradle build
```
