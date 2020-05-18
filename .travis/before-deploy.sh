#!/usr/bin/env bash
mv ~/.gnupg ~/dot.gnupg  

openssl aes-256-cbc -K $encrypted_334a64c5d9b2_key -iv $encrypted_334a64c5d9b2_iv -in .travis/codesign.asc.enc -out .travis/codesign.asc -d
gpg --import .travis/codesign.asc
