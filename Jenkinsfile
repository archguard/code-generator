pipeline {
    environment {
        registry = 'hule0912/demo'
        registryCredential = 'DockerHubCredentials'
        dockerImage = ''
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
               }
            }
        }
        stage('Push image') {
            steps{
                script {
                      docker.withRegistry('https://registry.hub.docker.com', registryCredential ) {
                        dockerImage.push()
                      }
                 }
            }
        }
    }
}