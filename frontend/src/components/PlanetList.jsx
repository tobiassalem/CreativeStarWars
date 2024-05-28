import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import Footer from './Footer';

function PlanetList() {
  const [planets, setPlanets] = useState([]);

  useEffect(() => {
    // axios.get('https://swapi.dev/api/planets')
    axios.get('planets.json')
      .then(response => {
        console.log('response.data:', response.data);
        setPlanets(response.data.results);
      })
      .catch(error => {
        console.error('Error fetching characters:', error);
      });
  }, []);

  return (
    <React.Fragment>
    <div style={{ backgroundImage: "url('/images/planets.jpg')", padding:"40px"}}>
      <h2>Star Wars Planets</h2>
      <ul>
        {planets.map((planet, index) => (
          <li key={index}>
            <Link to={`/planets/${index+1}`}>{planet.name}</Link>
          </li>
        ))}
      </ul>
      <Footer />
    </div>
    </React.Fragment>
  );
}

export default PlanetList;