#!/bin/bash
mkdir -p DSiBashTestDir/textfiles DSiBashTestDir/zipfiles
chmod 700 ...
cd DSiBashTestDir/textfiles

now=$(date +"%d%m%y")
user=$(whoami)

for k in {0..999}; do
    file_id=$(( k + 1 ))
    zip_id=$(( k / 200 ))
    zip_id=$(( zip_id + 1 ))
    echo -n "file_${user}_${now}_${file_id}.txt|zipfile_${user}_${now}_${zip_id}.zip" > \
    "file_${user}_${now}_${file_id}.txt"
done

# cd ../..