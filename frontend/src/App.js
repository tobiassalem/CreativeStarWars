import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Dashboard from './components/Dashboard';
import CharacterList from './components/CharacterList';
import CharacterDetails from './components/CharacterDetails';
import PlanetList from './components/PlanetList';
import PlanetDetails from './components/PlanetDetails';
import StarshipList from './components/StarshipList';
import StarshipDetails from './components/StarshipDetails';

import './App.css';

function App() {
  return (
    <Router>
      <div className="app">
        <header className="app-header">
          <h1>The Star Wars Universe</h1>
          <div className='quote'>~ This is the way ~</div>
        </header>
        <main>
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="/characters" element={<CharacterList />} />
            <Route path="/characters/:id" element={<CharacterDetails />} />
            <Route path="/planets" element={<PlanetList />} />
            <Route path="/planets/:id" element={<PlanetDetails />} />
            <Route path="/starships" element={<StarshipList />} />
            <Route path="/starships/:id" element={<StarshipDetails />} />
          </Routes>
        </main>
      </div>
    </Router>
  );
}

export default App;
