import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Footer from './Footer';

function StarshipDetails() {
  const [starship, setStarship] = useState(null);
  const { id } = useParams();
  let navigate = useNavigate();

  useEffect(() => {
    axios.get(`https://swapi.dev/api/starships/${id}`)
      .then(response => {
        console.log('response.data starship:', response.data);
        setStarship(response.data);
      })
      .catch(error => {
        console.error(`Error fetching Starship with id ${id}:`, error);
      });
  }, [id]);

  if (!starship) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <Card sx={{ maxWidth: 640 }}>
        <CardMedia
          sx={{ height: 140 }}
          image="/images/spaceships.jpg"
          title="Illustration of a Star Wars starship"
        />
        <CardContent sx={{ backgroundImage: "url('/images/texture-background.png')" }}> 
          <Typography gutterBottom variant="h5" component="div" className='dataHeader'>
            {starship.name}
          </Typography>
          <Typography variant="body2" color="text.secondary" component={"div"}>
            <p><span className='dataField'>Model:</span> <span className='dataValue'>{starship.model}</span></p>
            <p><span className='dataField'>Manufacturer:</span> <span className='dataValue'>{starship.manufacturer}</span></p>
            <p><span className='dataField'>Diameter:</span> <span className='dataValue'>{getData(starship.diameter)}</span></p>
            <p><span className='dataField'>Cost:</span> <span className='dataValue'>{getData(starship.cost_in_credits)}</span></p>
            <p><span className='dataField'>Length:</span> <span className='dataValue'>{getData(starship.length)}</span></p>
            <p><span className='dataField'>Max atmos speed:</span> <span className='dataValue'>{getData(starship.max_atmosphering_speed)}</span></p>
            <p><span className='dataField'>Crew:</span> <span className='dataValue'>{getBoolData(starship.crew)}</span></p>
            <p><span className='dataField'>Passengers:</span> <span className='dataValue'>{getData(starship.passengers)}</span></p>
            <p><span className='dataField'>Cargo capacity:</span> <span className='dataValue'>{getData(starship.cargo_capacity)}</span></p>
            <p><span className='dataField'>Consumables:</span> <span className='dataValue'>{getData(starship.consumables)}</span></p>
            <p><span className='dataField'>Created:</span> <span className='dataValue'>{starship.created}</span></p>
            <p><span className='dataField'>Updated:</span> <span className='dataValue'>{starship.edited}</span></p>
          </Typography>
        </CardContent>
        <CardActions>
          <Button size="small" onClick={() => navigate(-1)}>Go back</Button>
        </CardActions>
      </Card>

      <Footer />
    </div>
  );
}

function getData(dataValue) {
  return dataValue ? dataValue : "Unknown";
}

function getBoolData(dataValue) {
  return dataValue && dataValue === '1' ? 'Yes' : 'No';
}

export default StarshipDetails;