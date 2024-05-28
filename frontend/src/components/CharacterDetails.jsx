import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';
import Grid from '@mui/material/Grid';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Footer from './Footer';

function CharacterDetails() {
  const [character, setCharacter] = useState(null);
  const { id } = useParams();
  let navigate = useNavigate();

  useEffect(() => {
    axios.get(`https://swapi.dev/api/people/${id}`)
      .then(response => {
        console.log('response.data character:', response.data);
        setCharacter(response.data);
      })
      .catch(error => {
        console.error(`Error fetching character with id ${id}:`, error);
      });
  }, [id]);

  if (!character) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <Grid container spacing={3} justifyContent="center">
      <Grid item key={1}>
        <Card sx={{ maxWidth: 420 }}>
          <CardMedia
            sx={{ height: 140 }}
            image="/images/siluett.jpg"
            title="Silhouette of a Star Wars character"
          />
          <CardContent sx={{ backgroundImage: "url('/images/texture-background.png')" }}> 
            <Typography gutterBottom variant="h5" component="div" className='dataHeader'>
              {character.name}
            </Typography>
            <Typography variant="body2" color="text.secondary" component={"div"}>
              <p><span className='dataField'>Height:</span> <span className='dataValue'>{character.height} cm</span></p>
              <p><span className='dataField'>Mass:</span> <span className='dataValue'>{character.mass} kg</span></p>
              <p><span className='dataField'>Hair Color:</span> <span className='dataValue'>{getData(character.hair_color)}</span></p>
              <p><span className='dataField'>Skin Color:</span> <span className='dataValue'>{getData(character.skin_color)}</span></p>
              <p><span className='dataField'>Eye Color:</span> <span className='dataValue'>{getData(character.eye_color)}</span></p>
              <p><span className='dataField'>Birth Year:</span> <span className='dataValue'>{getData(character.birth_year)}</span></p>
              <p><span className='dataField'>Gender:</span> <span className='dataValue'>{character.gender}</span></p>
              <p><span className='dataField'>Created:</span> <span className='dataValue'>{character.created}</span></p>
              <p><span className='dataField'>Updated:</span> <span className='dataValue'>{character.edited}</span></p>
            </Typography>
          </CardContent>
          <CardActions>
            <Button size="small" onClick={() => navigate(-1)}>Go back</Button>
          </CardActions>
        </Card>
        </Grid>

        <Grid item key={2}>
        <Card sx={{ maxWidth: 420 }}>
          <CardMedia
            sx={{ height: 140 }}
            image="/images/movie.jpg"
            title="Star Wars movie poster"
          />
          <CardContent sx={{ backgroundImage: "url('/images/texture-background.png')" }}> 
            <Typography gutterBottom variant="h5" component="div" className='dataHeader'>
              Films with {character.name}
            </Typography>
            <Typography variant="body2" color="text.secondary" component={"div"}>
              <ul>
                {character.films.map((f, index) => (
                  <li key={index}>
                    <a href={`${f}`} target="_blank" rel="noreferrer">{f}</a>
                  </li>
                ))}
              </ul>
            </Typography>
          </CardContent>
        </Card>
        </Grid>
      </Grid>
      <Footer />
    </div>
  );
}

function getData(dataValue) {
  return dataValue ? dataValue : "Unknown";
}

export default CharacterDetails;