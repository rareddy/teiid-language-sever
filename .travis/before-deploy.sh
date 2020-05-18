#!/usr/bin/env bash
mv ~/.gnupg ~/dot.gnupg  

openssl aes-256-cbc -K $encrypted_334a64c5d9b2_key -iv $encrypted_334a64c5d9b2_iv -in .travis/codesign.gpg.enc -out .travis/codesign.gpg -d
gpg --import .travis/codesign.gpg
