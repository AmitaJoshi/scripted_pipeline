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
    }
  }
}
