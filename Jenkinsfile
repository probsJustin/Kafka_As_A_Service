pipeline {
  agent any
  stages {
    stage('Run Kafka Build Script') {
	  steps {
        sh 'chmod 777 -R ./*'
      }
      steps {
        sh './build.sh'
      }
    }
  }
}