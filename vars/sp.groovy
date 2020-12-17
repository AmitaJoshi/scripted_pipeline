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
        def fileWrite = libraryResource nodesampleapp/script.sh
writeFile file: nodesampleapp/script.sh, text: fileWrite
        sh '''
cd nodesampleapp
sh script.sh
        '''
        
      }
    }
  }
}
