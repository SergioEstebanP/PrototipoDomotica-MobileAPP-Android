pipeline {
    agent any

    stages {
        stage('Building') {
            steps {
                echo 'Building ...'
            }
        }
        stage('Testing') {
            steps {
                echo 'Testing ...'
            }
        }
        stage('Sonar Cube Analysis') {
            steps {
                echo 'Testing ...'
            }
        }
        stage('Deploying results') {
            steps {
                echo 'Deploying ...'
            }
        }
    }
}
