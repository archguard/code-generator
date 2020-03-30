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
    }
}
