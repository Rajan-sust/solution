#!/bin/bash

mkdir -p DSiBashTestDir/textfiles DSiBashTestDir/zipfiles
chmod 600 DSiBashTestDir/textfiles

cd DSiBashTestDir/textfiles

now=$(date +"%d%m%y")
user=$(whoami)

for k in {0..999}; do
    file_id=$(( k + 1 ))
    zip_id=$(( k / 200 + 1 ))
    echo -n "file_${user}_${now}_${file_id}.txt|zipfile_${user}_${now}_${zip_id}.zip" > \
    "file_${user}_${now}_${file_id}.txt"
done

cd ../zipfiles

for i in {0..4}; do
    zip_id=$(( i + 1 ))
    for j in {1..200}; do
        file_id=$((200 * i + j))
        if [[ -f "zipfile_${user}_${now}_${zip_id}.zip" ]]; then
            zip -u "zipfile_${user}_${now}_${zip_id}.zip" "../textfiles/file_${user}_${now}_${file_id}.txt"
        else
            zip "zipfile_${user}_${now}_${zip_id}.zip" "../textfiles/file_${user}_${now}_${file_id}.txt"
        fi
    done
done
