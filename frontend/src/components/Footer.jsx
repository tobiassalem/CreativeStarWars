import React from 'react';
import { Link } from 'react-router-dom';
import './Footer.css';

const Footer = () => {
  return (
    <div className='footer'>
      <div className="footer-content">
        <p>&copy; 1977 - 2024 Lucas Arts. All rights reserved.
        </p>
        <Link to="/" className="home-link">
          Home
        </Link>
      </div>
    </div>
  );
};

export default Footer;