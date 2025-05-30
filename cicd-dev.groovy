node('linux')
{
  stage ('Poll') {
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/scdocport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/scdocport.git'), string(name: 'PORT_DESCRIPTION', value: 'scdoc is a simple man page generator for POSIX systems written in C99.' ), string(name: 'BUILD_LINE', value: 'DEV') ]
  }
}
