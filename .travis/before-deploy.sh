#!/usr/bin/env bash
mv ~/.gnupg ~/dot.gnupg  

openssl aes-256-cbc -K $encrypted_1f0e3118fac8_key -iv $encrypted_1f0e3118fac8_iv -in .travis/codesign.gpg.enc -out .travis/codesign.gpg -d
gpg --import .travis/codesign.gpg