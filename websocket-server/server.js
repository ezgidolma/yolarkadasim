const http = require('http');
const { Server } = require('socket.io');

const server = http.createServer();
const io = new Server(server);

io.on('connection', (socket) => {
  console.log('Bir kullanıcı bağlandı');

  socket.on('message', (message) => {
    console.log('Alınan mesaj:', message);
    io.emit('message', message); // Tüm bağlı istemcilere mesajı yayınla
  });

  socket.on('disconnect', () => {
    console.log('Bir kullanıcı ayrıldı');
  });
});

const PORT = process.env.PORT || 3030;
server.listen(PORT, () => {
  console.log(`Sunucu ${PORT} portunda çalışıyor`);
});
