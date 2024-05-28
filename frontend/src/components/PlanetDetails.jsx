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

function PlanetDetails() {
  const [planet, setPlanet] = useState(null);
  const { id } = useParams();
  let navigate = useNavigate();

  useEffect(() => {
    axios.get(`https://swapi.dev/api/planets/${id}`)
      .then(response => {
        setPlanet(response.data);
      })
      .catch(error => {
        console.error(`Error fetching planet with id ${id}:`, error);
      });
  }, [id]);

  if (!planet) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <Card sx={{ maxWidth: 640 }}>
        <CardMedia
          sx={{ height: 140 }}
          image="/images/planets.jpg"
          title="Illustration of a Star Wars planet"
        />
        <CardContent sx={{ backgroundImage: "url('/images/texture-background.png')" }}> 
          <Typography gutterBottom variant="h5" component="div" className='dataHeader'>
            {planet.name}
          </Typography>
          <Typography variant="body2" color="text.secondary" component={"div"}>
            <p><span className='dataField'>Rotation period:</span> <span className='dataValue'>{planet.rotation_period}</span></p>
            <p><span className='dataField'>Orbital period:</span> <span className='dataValue'>{planet.orbital_period}</span></p>
            <p><span className='dataField'>Diameter:</span> <span className='dataValue'>{getData(planet.diameter)}</span></p>
            <p><span className='dataField'>Climate:</span> <span className='dataValue'>{getData(planet.climate)}</span></p>
            <p><span className='dataField'>Gravity:</span> <span className='dataValue'>{getData(planet.gravity)}</span></p>
            <p><span className='dataField'>Terrain:</span> <span className='dataValue'>{getData(planet.terrain)}</span></p>
            <p><span className='dataField'>Surface water:</span> <span className='dataValue'>{getBoolData(planet.surface_water)}</span></p>
            <p><span className='dataField'>Populate:</span> <span className='dataValue'>{getData(planet.population)}</span></p>
            <p><span className='dataField'>Created:</span> <span className='dataValue'>{planet.created}</span></p>
            <p><span className='dataField'>Updated:</span> <span className='dataValue'>{planet.edited}</span></p>
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

export default PlanetDetails;