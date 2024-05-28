import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import Footer from './Footer';

function StarshipList() {
  const [starships, setStarships] = useState([]);

  useEffect(() => {
    // axios.get('https://swapi.dev/api/starships')
    axios.get('starships.json')
      .then(response => {
        console.log('response.data:', response.data);
        setStarships(response.data.results);
      })
      .catch(error => {
        console.error('Error fetching characters:', error);
      });
  }, []);

  return (
    <React.Fragment>
    <div style={{ backgroundImage: "url('/images/spaceships.jpg')", padding:"40px"}}>
      <h2>Star Wars Starships</h2>
      <ul>
        {starships.map((s, index) => (
          <li key={index}>
            <Link to={`/starships/${index+2}`}>{s.name}</Link>
          </li>
        ))}
      </ul>
      <Footer />
    </div>
    </React.Fragment>
  );
}

export default StarshipList;