pipeline {
    environment {
        registry = 'hule0912/demo'
        registryCredential = 'DockerHubCredentials'
        dockerImage = ''
	dev_env = 'ag-dev'
	qa_env = 'ag-qa'
	uat_env = 'ag-uat'
	prod_env = 'ag-prod'
    }
    tools {
        jdk 'openjdk-11'
    }
    agent any
    stages {
        stage('Build') {
            steps {
                sh './gradlew build'
            }
        }
        stage('Build Image') {
            steps{
               script {
                 dockerImage = docker.build registry + ":$BUILD_NUMBER"
                 docker.withRegistry('https://registry.hub.docker.com', registryCredential ) {
                     dockerImage.push()
                 }
               }
            }
        }
	stage('Deploy dev') {
	    steps{
	        sh 'kubectl apply -f ./deploy_k8s.yml -n dev_env'
	    }
	}
    }
}
