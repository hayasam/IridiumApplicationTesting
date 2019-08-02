#!/usr/bin/env bash
if [[ $TEST_SUITE = "{'browsers':['PhantomJS'],'runNegTests':true,'runSimpleTests':true}" ]]; then
	ls -la
	./gradlew \
	-DisableSigning=false \
	-Psigning.keyId=${KeyID} \
	-Psigning.password=${KeyPassword} \
	-Psigning.secretKeyRingFile=${KeyFilename} \
	-PMavenVersion=${TRAVIS_TAG} uploadArchives
fi
