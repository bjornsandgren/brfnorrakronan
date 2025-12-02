import express from 'express';
import expressBasicAuth from 'express-basic-auth';
const app = express();

app.use(expressBasicAuth({
  users: { 'admin': 'Groda102' },
  challenge: true,
}))

app.use(express.static('dist'));
const PORT = process.env.PORT || 8081;

app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});