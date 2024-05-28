import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import Footer from './Footer';

function CharacterList() {
  const [characters, setCharacters] = useState([]);
  const [count, setCount] = useState(0);

  useEffect(() => {
    // axios.get('https://swapi.dev/api/people')
    axios.get('characters.json')
      .then(response => {
        console.log('response.data characters:', response.data);
        setCount(response.data.count);
        setCharacters(response.data.results);
      })
      .catch(error => {
        console.error('Error fetching characters:', error);
      });
  }, []);

  return (
    <React.Fragment>
    <div style={{ backgroundImage: "url('/images/texture-background.png')", padding:"40px"}}>
      <h2>Star Wars Characters ({count})</h2>
      <ul>
        {characters.map(character => (
          <li key={character.id}>
            <Link to={`/characters/${character.id}`}>{character.name}</Link>
          </li>
        ))}
      </ul>
      <Footer />
    </div>
    </React.Fragment>
  );
}

export default CharacterList;