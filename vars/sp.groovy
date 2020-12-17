def call(Map param){
env.REPO_NAME= param.REPO_NAME
env.BRANCH=param.BRANCH
  pipeline
  {
    node
    {
      stage("checkout_scm") 
      {
      sh '''
      rm -rf nodesampleapp
      ps -ef | grep "app1.js" | grep -v grep | awk '{print $2}' | xargs kill
      git clone $REPO_NAME
      cd nodesampleapp
      git checkout $BRANCH
      '''
      }
      stage("build")
      {
        //add your build steps here
      }
      stage("Static Code Analysis")
      {
        //add your build steps here
      }
      stage("deploy_to_dev_env")
      {
        sh '''
        cd nodesampleapp
        nohup node app1.js > /dev/null 2>&1 &
        '''
      }
    }
  }
}
