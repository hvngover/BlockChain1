const express = require('express');
const app = express();
const port = 4235;



app.get(('/'), (req,res) => {
  res.send('HI');
});

app.listen(port, () => {
  console.log(`Server working on port ${port}`);
});