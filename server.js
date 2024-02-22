const express = require('express');
const app = express();
const port = 4235;
const path = __dirname + '/src/frontend/';

app.use(express.static('src/frontend'));
app.get(('/'), (req,res) => {
  res.sendFile(path + 'index.html');
});

app.get(('/startvoting'), (req, res) => {
  res.sendFile(path + 'startvoting.html');
});

app.get(('/voting'), (req ,res) => {
  res.sendFile(path + 'voting.html');
});

app.listen(port, () => {
  console.log(`Server working on port ${port}`);
});