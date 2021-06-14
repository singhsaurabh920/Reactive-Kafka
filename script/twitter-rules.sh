type=${1?Error: Please provide request type}
if [ $type == "GET" ]
then
  echo "Get type called"
else
  echo "Post type called"
fi

post_rules(){
  curl -X POST 'https://api.twitter.com/2/tweets/search/stream/rules' \
  -H "Content-type: application/json" \
  -H "Authorization: Bearer $BEARER_TOKEN" -d \
  '{
    "add": [
      {"value": "covid case", "tag": "covid case in india"}
    ]
  }' json_pp
}

get_rules(){
  curl -X GET 'https://api.twitter.com/2/tweets/search/stream/rules' \
  -H "Authorization: Bearer $BEARER_TOKEN"
}

case "$type" in
   "GET")
    get_rules
   ;;
   "POST")
    post_rules
   ;;
esac