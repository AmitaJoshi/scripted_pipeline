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
        sh '''
          cd nodesampleapp
          eslint app1.js
        '''
        //add your build steps here
      }
      stage("deploy_to_dev_env")
      {
        def fileWrite = libraryResource "script.sh"
        writeFile file: "${WORKSPACE}/nodesampleapp/script.sh", text: fileWrite
        sh '''
cd nodesampleapp
chmod -R 777 .
sh script.sh
        '''
        
      }
    }
  }
}
