import React from 'react';
import { AiFillGithub } from 'react-icons/ai';
import styled, { css } from 'styled-components';
import { Col, Row } from 'antd';

const container = css`
	max-width: 1000px;
	width: 100%;
	display: flex;
	margin: 10px auto;
`;

const FooterComponent = styled.footer`
	width: 100%;
	display: flex;
	justify-content: center;
	background: #f8f8f8;
	padding: 10px 0;
	.content {
		${container}
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		.rep {
			display: flex;
			align-items: center;
			> div {
				margin-right: 0.5rem;
			}
		}
		a {
			display: flex;
			align-items: center;
			font-style: italic;
			margin: 0.5rem;
			color: #333;
			text-decoration: none;
			:active,
			:link,
			:visited {
				color: #333;
			}
			:hover {
				color: #0073e6;
			}
		}
		h5 {
			margin-right: 1rem;
			color: #333;
			display: inline;
		}
	}
`;

function Footer() {

  const FOOTER_LIST = {
    copyright: 'MES © 2024 All rights reserved.',
    be1: [
      { name: 'OHyungSang', repository: 'https://github.com/ohy1023' },
    ]
  };

  return (
    <FooterComponent>
      <div className="content">
        <Row style={{ width: '100%' }} justify="center" align="middle">
          <Col span={12} style={{ textAlign: 'center' }}>
            <h5>DEVELOPER</h5>
            <div className="rep">
              {FOOTER_LIST.be1.map((v, i) => (
                <a href={v.repository} key={i} target="_blank" rel="noreferrer">
                  <AiFillGithub style={{ marginRight: '0.5rem' }} />
                  <span>{v.name}</span>
                </a>
              ))}
            </div>
          </Col>
          <Col span={12} style={{ display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
            <div style={{ color: '#757575', textAlign: 'center' }}>{FOOTER_LIST.copyright}</div>
          </Col>
        </Row>
      </div>
    </FooterComponent>
  );
}

export default Footer;
