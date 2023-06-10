#!/bin/bash

# 빌드할 모듈 목록
modules=("api-gateway" "user" "seller" "product" "order" "payment" "shipment")

# 모든 모듈을 순회하며 빌드
for module in "${modules[@]}"
do
  echo "[$module 모듈 빌드 시작]"
  cd "$module" || exit 1
  ./gradlew clean build
  cd ..
  echo "[$module 모듈 빌드 완료]"
done
