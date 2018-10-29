pipeline {
    agent any
    stages {
        stage('Get source code') {
            steps {
                echo 'getting source code ...'
                cat jenkinsfile.groovy
            }
        }
        stage('Build') {
            steps {
                echo 'building ...'
            }
        }
        stage('Test') {
            steps {
                echo 'testing ...'
            }
        }
        stage('Deploy') {
            steps {
                echo 'deploying results ...'
            }
        }
    }
}
