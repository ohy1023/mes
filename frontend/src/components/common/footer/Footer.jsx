import React from 'react';
import { AiFillGithub } from 'react-icons/ai';
import styled from 'styled-components';
import { css } from 'styled-components';
import { Col, Row } from 'antd';

const container = css`
	max-width: 1400px;
	width: 100%;
	display: flex;
	margin: 30px auto;
`;

const FooterComponent = styled.footer`
	width: 100%;
	height: 180px;
	display: flex;
	justify-content: center;
	background: #e2e2e2;
	.content {
		${container}
		display: flex;
		// align-items: center;
		// justify-content: space-between;
		.rep {
			display: flex;
			// align-items: center;
			> div {
				:nth-child(1) {
					padding: 0 0.5rem;
				}
			}
		}
		a {
			display: flex;
			// justify-content: center;
			padding: 0.125rem 0.125rem;
			align-items: left;
			font-style: italic;
			margin: 0.125rem 0.25rem;
			color: #333;
			:active,
			:link,
			:visited {
				color: #333;
			}
		}
		img {
			width: 110px;
			height: 80px;
		}
		> div {
			:nth-child(1) {
				display: flex;
				align-items: center;
				justify-content: center;
			}
			> div {
				margin: 0.5rem 0;
			}
		}
	}
`;

function Footer() {

  const FOOTER_LIST = {
    repository: 'https://github.com/ohy1023/mes',
    copyright: 'MES © 2023 All rights reserved.',
    be1: [
      { name: 'OHyungSang', repository: 'https://github.com/ohy1023' },
    ]
  };

  return (
    <FooterComponent>
      <div className="content">
      <Row style={{width : '100%'}}>
        <Col span={6}>
          <div>
            <Row><h5>ABOUT</h5></Row>
            <ul>
              <li>
                <a href={FOOTER_LIST.repository1} target="_blank" rel="noreferrer">
                  Github Repository
                </a>
              </li>
            </ul>
          </div>
        </Col>
        
        <Col span={12}>
          <div>
            <Row><h5>DEVELOPER</h5></Row>
            <div className="rep">
              {FOOTER_LIST.be1.map((v, i) => (
                <a href={v.repository} key={i} target="_blank" rel="noreferrer">
                  <div>
                    <AiFillGithub />
                  </div>
                  <div>{v.name}</div>
                </a>
              ))}
            </div>
          </div>
        </Col>

        <Col span={4}>
          <div style={{color : '#757575', marginTop: '90px'}}>{FOOTER_LIST.copyright}</div>
        </Col>
      </Row>
      </div>
    </FooterComponent>
  );
}

export default Footer;