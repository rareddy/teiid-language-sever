#!/usr/bin/env bash
mv ~/.gnupg ~/dot.gnupg  

openssl aes-256-cbc -K $encrypted_24431db6f336_key -iv $encrypted_24431db6f336_iv -in codesign.gpg.enc -out .travis/codesign.gpg -d
gpg --import .travis/codesign.gpg