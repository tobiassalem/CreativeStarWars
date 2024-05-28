import React from 'react';
import { Grid, Card, CardMedia, CardContent, Typography } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import Footer from './Footer';

const Dashboard = () => {
  const navigate = useNavigate();

  const handleCardClick = (detailView) => {
    navigate(`/${detailView}`);
  };

  const cardData = [
    {
      image: '/images/characters.jpg',
      title: 'Characters',
      description: 'The good, the bad, and the ugly of the galaxy',
      detailView: 'characters',
    },
    {
      image: '/images/planets.jpg',
      title: 'Planets',
      description: 'The amazing worlds of the galaxy',
      detailView: 'planets',
    },
    {
      image: '/images/spaceships.jpg',
      title: 'Starships',
      description: 'The amazing means of transportion across the galaxy',
      detailView: 'starships',
    },
  ];

  return (
    <Grid container spacing={3} justifyContent="center">
      {cardData.map((card, index) => (
        <Grid item key={index}>
          <Card sx={{ ':hover': { boxShadow: 40 }, maxWidth: 380}} onClick={() => handleCardClick(card.detailView)}>
            <CardMedia
              component="img"
              height="180"
              width="300"
              image={card.image}
              alt={card.description}
            />
            <CardContent>
            <Typography gutterBottom variant="h5" component="div" className='dataField'>
                {card.title}
            </Typography>
              <Typography variant="body2" color="textSecondary" component="p">
                {card.description}
              </Typography>
            </CardContent>
          </Card>
        </Grid>
      ))}
      <Footer />
    </Grid>
  );
};

export default Dashboard;