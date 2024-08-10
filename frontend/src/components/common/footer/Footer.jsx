import React from 'react';
import { AiFillGithub } from 'react-icons/ai';
import { Col, Row } from 'antd';
import styles from './Footer.module.css';  // CSS 모듈 임포트

function Footer() {

  const FOOTER_LIST = {
    copyright: 'MES © 2024 All rights reserved.',
    be1: [
      { name: 'OHyungSang', repository: 'https://github.com/ohy1023' },
    ]
  };

  return (
    <footer className={styles.footer}>
      <div className={`${styles.content} ${styles.container}`}>
        <Row style={{ width: '100%' }} justify="center" align="middle">
          <Col span={12} className={styles.col}>
            <h5>DEVELOPER</h5>
            <div className={styles.rep}>
              {FOOTER_LIST.be1.map((v, i) => (
                <a href={v.repository} key={i} target="_blank" rel="noreferrer">
                  <AiFillGithub className={styles.icon} />
                  <span>{v.name}</span>
                </a>
              ))}
            </div>
          </Col>
          <Col span={12} className={styles.col}>
            <div className={styles.copyright}>{FOOTER_LIST.copyright}</div>
          </Col>
        </Row>
      </div>
    </footer>
  );
}

export default Footer;
