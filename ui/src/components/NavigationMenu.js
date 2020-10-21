import React from 'react'
import {Link} from 'react-router-dom'

function NavigationMenu(props) {
    return (
        <div>
            <div className="font-bold py-3">
                Menu body
            </div>
            <ul>
                <li>
                    <Link to="/" className="text-blue-500 py-3 border-t border-b block" onClick={props.closeMenu}>
                        Home page
                    </Link>
                </li>
                <li>
                    <Link to="/first-service" className="text-blue-500 py-3 border-b block" onClick={props.closeMenu}>
                        Service 1
                    </Link>
                </li>
                <li>
                    <Link to="/second-service" className="text-blue-500 py-3 border-b block" onClick={props.closeMenu}>
                        Service 2
                    </Link>
                </li>
            </ul>
        </div>
    )
}

export default NavigationMenu