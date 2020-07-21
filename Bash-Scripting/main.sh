#!/bin/bash

mkdir -p DSiBashTestDir/textfiles DSiBashTestDir/zipfiles
cd DSiBashTestDir/textfiles

now=$(date +"%d%m%y")
user=$(whoami)

for k in {0..999}; do
    file_id=$(( k + 1 ))
    zip_id=$(( k / 200 + 1 ))
    fname="file_${user}_${now}_${file_id}.txt"
    zname="zipfile_${user}_${now}_${zip_id}.zip"
    echo -n "${fname}|${zname}" > "${fname}"
done

cd ../zipfiles

for i in {0..4}; do
    zip_id=$(( i + 1 ))
    for j in {1..200}; do
        file_id=$(( 200 * i + j ))
        fpath="../textfiles/file_${user}_${now}_${file_id}.txt"
        zname="zipfile_${user}_${now}_${zip_id}.zip"
        if [[ -f "${zname}" ]]; then
            zip -u "${zname}" "${fpath}"
        else
            zip "${zname}" "${fpath}"
        fi
    done
done

cd ..
chmod 700 textfiles
cd ..