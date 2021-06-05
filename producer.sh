#!/bin/bash
userID=$(shuf -i 0-100 -n 1);
salary=$(shuf -i 20000-50000 -n 1);
emailID="user"$userID"@gmail.com";
requestData='{"username":"user'$userID'","email":"'$emailID'","salary":'$salary'}';
echo $requestData;
curl -v -X POST -H 'Content-Type: application/json' -d $requestData localhost:8071/kafka/publish/user