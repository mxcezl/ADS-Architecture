cd DB_Pat
docker compose up -d
cd ..

cd DB_Traces
docker compose up -d
cd ..

cd wscreate
./runDocker.bat
cd ..

cd wsupdatedelete
./runDocker.bat
cd ..

cd wssearch
./runDocker.bat
