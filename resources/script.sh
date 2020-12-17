rm -rf nodesampleapp
      ps -ef | grep "app1.js" | grep -v grep | awk '{print $2}' | xargs kill
      nohup node app1.js > /dev/null 2>&1 &
