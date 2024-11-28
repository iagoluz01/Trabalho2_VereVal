--AREAS
INSERT INTO areas (id, nome)
VALUES ('d6444e7f-d61a-4940-93c1-41c9c04f2a0d', 'Câncer');
--AREAS/Câncer
INSERT INTO areas (id, nome, area_principal_id)
VALUES (
        '605a204a-3817-4f45-b5bf-a528fa6b718e',
        'Câncer de Pele',
        'd6444e7f-d61a-4940-93c1-41c9c04f2a0d'
    ),
    (
        '7c4b1733-3e75-4d69-91fa-44b206bef62b',
        'Câncer de Mama',
        'd6444e7f-d61a-4940-93c1-41c9c04f2a0d'
    ),
    (
        '9fc50812-b39d-4aa8-aeeb-fc81746227ba',
        'Câncer Colorretal',
        'd6444e7f-d61a-4940-93c1-41c9c04f2a0d'
    ),
    (
        '10d46dce-400c-44eb-ae34-20d5ca080a34',
        'Câncer de Pulmão',
        'd6444e7f-d61a-4940-93c1-41c9c04f2a0d'
    ),
    (
        'f2234708-1b36-421b-b97c-0c583058be13',
        'Leucemia',
        'd6444e7f-d61a-4940-93c1-41c9c04f2a0d'
    );
--AREAS/Câncer/Câncer de Pele
INSERT INTO areas (id, nome, area_principal_id)
VALUES (
        '686a0331-bfaa-4d79-aa1a-51c43d078e69',
        'Carcinoma Basocelualr',
        '605a204a-3817-4f45-b5bf-a528fa6b718e'
    ),
    (
        '37c61337-53da-4a4d-bb44-a114634fd7ae',
        'Carcinoma Espinocelular',
        '605a204a-3817-4f45-b5bf-a528fa6b718e'
    ),
    (
        'ca826e78-a1d3-4711-aa7f-634499a4d2c3',
        'Melanoma',
        '605a204a-3817-4f45-b5bf-a528fa6b718e'
    );
--AREAS/Câncer/Câncer de Mama
INSERT INTO areas (id, nome, area_principal_id)
VALUES (
        'cb91389f-2ac3-4c07-ac76-11ec032f72e5',
        'Carcinoma Ductal Invasivo',
        '7c4b1733-3e75-4d69-91fa-44b206bef62b'
    ),
    (
        '9fb5f9c0-229f-4fba-bc82-1ee950ee5d28',
        'Carcinoma Lobular Invasivo',
        '7c4b1733-3e75-4d69-91fa-44b206bef62b'
    ),
    (
        'b68c1f41-485f-4550-bde1-f0822f8ae08b',
        'Carcinoma Ductal In Situ',
        '7c4b1733-3e75-4d69-91fa-44b206bef62b'
    );
--AREAS/Câncer/Câncer de Colorretal
INSERT INTO areas (id, nome, area_principal_id)
VALUES (
        '35ea00db-da43-4d74-a6b4-b893f2b25c22',
        'Adenocarcinoma Colorretal',
        '9fc50812-b39d-4aa8-aeeb-fc81746227ba'
    ),
    (
        '52b9d98a-dd34-4eff-b0fb-f988bc082680',
        'Carcinoma de Células Escamosas Colorretal',
        '9fc50812-b39d-4aa8-aeeb-fc81746227ba'
    ),
    (
        '49cb4dbf-11f0-4e35-9298-d93d887f00d5',
        'Linfoma Colorretal',
        '9fc50812-b39d-4aa8-aeeb-fc81746227ba'
    );
--AREAS/Câncer/Câncer de Pulmão
INSERT INTO areas (id, nome, area_principal_id)
VALUES (
        '7f516bfc-90ae-45d7-9b40-abfe2cd5157e',
        'Não Pequenas Células',
        '10d46dce-400c-44eb-ae34-20d5ca080a34'
    ),
    (
        '06bfc8f5-bbce-4373-b905-f60a1edc15b8',
        'Pequenas Células',
        '10d46dce-400c-44eb-ae34-20d5ca080a34'
    );
--AREAS/Câncer/Leucemia
INSERT INTO areas (id, nome, area_principal_id)
VALUES (
        '108338b2-c30c-4c19-9650-31629f386f65',
        'Mieloide Aguda',
        'f2234708-1b36-421b-b97c-0c583058be13'
    ),
    (
        '1fbd2fd8-005e-442d-ae90-56a88c0d00e8',
        'Mieloide Crônica',
        'f2234708-1b36-421b-b97c-0c583058be13'
    ),
    (
        '3441464d-c251-42b1-8af1-a2f449f20cc2',
        'Linfocítica Aguda',
        'f2234708-1b36-421b-b97c-0c583058be13'
    ),
    (
        'd79011de-7c18-4d1c-94af-a7f7c27603b2',
        'Linfocítica Crônica',
        'f2234708-1b36-421b-b97c-0c583058be13'
    );
--AREAS/Câncer/Câncer de Pele/Melanoma
INSERT INTO areas (id, nome, area_principal_id)
VALUES (
        'ed96d3e5-329f-4f2f-b187-0b864b375a85',
        'Melanoma Cutâneo',
        '605a204a-3817-4f45-b5bf-a528fa6b718e'
    ),
    (
        'b10ba99d-3756-487a-b3b7-d65e5331101e',
        'Melanoma Ocular',
        '605a204a-3817-4f45-b5bf-a528fa6b718e'
    );
--AREAS/Câncer/Câncer de Pele/Carcinoma Espinocelular
INSERT INTO areas (id, nome, area_principal_id)
VALUES (
        '1dd30d84-7d77-48ef-8ff8-29c590140d98',
        'In Situ',
        '37c61337-53da-4a4d-bb44-a114634fd7ae'
    ),
    (
        '35dc64b5-eb43-4da4-a9a5-82e5d9065167',
        'Invasivo',
        '37c61337-53da-4a4d-bb44-a114634fd7ae'
    ),
    (
        '3e18c63e-ef48-4439-bf23-9ed8cbf7708b',
        'Cutâneo',
        '37c61337-53da-4a4d-bb44-a114634fd7ae'
    ),
    (
        '3d4ba63c-9670-424e-9858-c9e276aadbdc',
        'Não Cutâneo',
        '37c61337-53da-4a4d-bb44-a114634fd7ae'
    );
--AREAS/Câncer/Câncer de Pele/Carcinoma Basocelualr
--AREAS/Câncer/Câncer de Mama/Carcinoma Ductal Invasivo
INSERT INTO areas (id, nome, area_principal_id)
VALUES (
        '12796401-c4d3-47a5-9c9e-87cfb9aafb2a',
        'Grau Nuclear Baixo',
        'cb91389f-2ac3-4c07-ac76-11ec032f72e5'
    ),
    (
        'c3fa5bc9-7a25-4b54-988e-d6ebe43b52f8',
        'Grau Nuclear Intermediário',
        'cb91389f-2ac3-4c07-ac76-11ec032f72e5'
    ),
    (
        '02a4362a-4dd3-4949-8818-8432d19e2eec',
        'Grau Nuclear Alto',
        'cb91389f-2ac3-4c07-ac76-11ec032f72e5'
    ),
    (
        'e4f7b8aa-e0fa-4b74-aa9f-58d122296fc2',
        'Componente de Carcinoma In Situ',
        'cb91389f-2ac3-4c07-ac76-11ec032f72e5'
    ),
    (
        'cc8a3c72-3696-4865-8b9b-15c28382ebba',
        'Subtipo Especial',
        'cb91389f-2ac3-4c07-ac76-11ec032f72e5'
    );
--AREAS/Câncer/Câncer de Colorretal/Adenocarcinoma Colorretal
INSERT INTO areas (id, nome, area_principal_id)
VALUES (
        '0961681d-1df4-409b-bfcb-08e13e52e2a3',
        'Adenocarcinoma de Cólon',
        '35ea00db-da43-4d74-a6b4-b893f2b25c22'
    ),
    (
        'aec5cd2e-2cca-4184-8a4d-f76c8e57af33',
        'Adenocarcinoma de Reto',
        '35ea00db-da43-4d74-a6b4-b893f2b25c22'
    );
--CRITÉRIOS INICIO--
INSERT INTO criterios (id, pergunta)
VALUES (
        'd6444e7f-d61a-4940-93c1-41c9c04f2a0e',
        'Melanoma confirmado por biópsia'
    ),
    (
        '018ed4d3-07a3-7895-b6ce-3276eb60d78e',
        'Presença de sintomas relacionados ao melanoma'
    ),
    (
        '1e90e7ca-7b0a-4693-99cf-a0743fb5132a',
        'Função adequada da medula óssea'
    ),
    (
        'e1697ebb-66f4-41cd-995e-8475e33633af',
        'Função renal adequada'
    ),
    (
        'f307495a-bf09-4926-a779-1ee434a411f9',
        'Tem melanoma uveal'
    ),
    (
        '52906f16-6408-441b-9480-5ce88bed7e23',
        'Tem uma doença autoimune clinicamente significante que precisou de tratamento sistêmico com agentes imunossupressivos nos últimos 2 anos'
    ),
    (
        '9d4bf6c3-0520-4cfe-86b3-82f0ee13f2aa',
        'Tem infecção não controlada pelo HIV, HBV ou HCV'
    ),
    (
        '3e09ddd0-e6cb-4e09-98b9-809388a84069',
        'Tem infecção não controlada pelo CLT'
    ),
    (
        '3eab993c-6c9e-4489-840d-e22bfc6f6f19',
        'Teve um transplante de órgão sólido'
    ),
    (
        'c88edb70-6015-4868-8e80-9f8ed0a8db0a',
        'Teve transplante de células hematopoiéticas'
    );
--CRITÉRIOS FIM--
--ESTUDOS INICIO--
--ESTUDO 1
INSERT INTO estudos (id, nome, link, informacoes, data_publicacao)
VALUES (
        'd6444e7f-d61a-4940-93c1-41c9c04f2a0f',
        'UM ESTUDO DE FASE 3 DE FIANLIMABE (ANTI-LAG-3) E CEMIPLIMABE VERSUS PEMBROLIZUMABE COMO TRATAMENTO ADJUVANTE EM PACIENTES COM MELANOMA DE ALTO RISCO COMPLETAMENTE RESSECADO',
        'https://www.google.com',
        'Informações sobre o estudo 1',
        '2021-01-01'
    );
--ESTUDO 1/areas
INSERT INTO ESTUDO_AREA (estudo_id, area_id)
VALUES (
        'd6444e7f-d61a-4940-93c1-41c9c04f2a0f',
        'ed96d3e5-329f-4f2f-b187-0b864b375a85'
    ),
    (
        'd6444e7f-d61a-4940-93c1-41c9c04f2a0f',
        'ca826e78-a1d3-4711-aa7f-634499a4d2c3'
    ),
    (
        'd6444e7f-d61a-4940-93c1-41c9c04f2a0f',
        '605a204a-3817-4f45-b5bf-a528fa6b718e'
    ),
    (
        'd6444e7f-d61a-4940-93c1-41c9c04f2a0f',
        'd6444e7f-d61a-4940-93c1-41c9c04f2a0d'
    );
--ESTUDO 1/criterios
INSERT INTO CRITERIOS_ESTUDOS (
        id,
        opcional,
        resposta_esperada,
        criterio_id,
        estudo_id
    )
VALUES (
        '91d57889-1a0a-4242-8b15-af2742f8e338',
        false,
        'SIM',
        'd6444e7f-d61a-4940-93c1-41c9c04f2a0e',
        'd6444e7f-d61a-4940-93c1-41c9c04f2a0f'
    ),
    (
        '018ed4d4-1c3e-7fa5-87a4-7ccb9e0e615c',
        true,
        'SIM',
        '018ed4d3-07a3-7895-b6ce-3276eb60d78e',
        'd6444e7f-d61a-4940-93c1-41c9c04f2a0f'
    ),
    (
        '1eb3cc23-6a20-4bba-823f-f4c0a9922ee9',
        false,
        'SIM',
        'f307495a-bf09-4926-a779-1ee434a411f9',
        'd6444e7f-d61a-4940-93c1-41c9c04f2a0f'
    ),
    (
        '17fedd23-d0dc-4e75-825a-f5c799eeb6a7',
        false,
        'NAO',
        '52906f16-6408-441b-9480-5ce88bed7e23',
        'd6444e7f-d61a-4940-93c1-41c9c04f2a0f'
    );
--ESTUDO 2
INSERT INTO estudos (id, nome, link, informacoes, data_publicacao)
VALUES (
        '1896428f-1aaa-4b1f-a9c9-1398da088a52',
        'Estudo de Fase III do [Nome da Droga] em Pacientes com [Condição Médica]',
        'https://www.google.com',
        'INFORMACOES',
        '2023-01-01'
    );
--ESTUDO 2/Areas
INSERT INTO ESTUDO_AREA (estudo_id, area_id)
VALUES (
        '1896428f-1aaa-4b1f-a9c9-1398da088a52',
        'd6444e7f-d61a-4940-93c1-41c9c04f2a0d'
    ),
    (
        '1896428f-1aaa-4b1f-a9c9-1398da088a52',
        '7c4b1733-3e75-4d69-91fa-44b206bef62b'
    ),
    (
        '1896428f-1aaa-4b1f-a9c9-1398da088a52',
        'cb91389f-2ac3-4c07-ac76-11ec032f72e5'
    ),
    (
        '1896428f-1aaa-4b1f-a9c9-1398da088a52',
        '12796401-c4d3-47a5-9c9e-87cfb9aafb2a'
    );
--ESTUDO 2/criterios
INSERT INTO CRITERIOS_ESTUDOS (
        id,
        opcional,
        resposta_esperada,
        criterio_id,
        estudo_id
    )
VALUES (
        '0688e3f1-174e-4670-9d88-1beba327e1c9',
        false,
        'SIM',
        'd6444e7f-d61a-4940-93c1-41c9c04f2a0e',
        '1896428f-1aaa-4b1f-a9c9-1398da088a52'
    );
--ESTUDO 3
INSERT INTO estudos (id, nome, link, informacoes, data_publicacao)
VALUES (
        '447b1927-5b88-4402-8704-457eedddd163',
        'Estudo de Dose Crescente do [Nome da Droga] em [Condição Médica]',
        'https://www.google.com',
        'Informações sobre o estudo 3 ---- informacoes sobre [Condição Médica] e [Nome da Droga]',
        '2021-01-02'
    );
--ESTUDO 3/areas
INSERT INTO ESTUDO_AREA (estudo_id, area_id)
VALUES (
        '447b1927-5b88-4402-8704-457eedddd163',
        'f2234708-1b36-421b-b97c-0c583058be13'
    ),
    (
        '447b1927-5b88-4402-8704-457eedddd163',
        '108338b2-c30c-4c19-9650-31629f386f65'
    );
--ESTUDO 3/criterios
INSERT INTO CRITERIOS_ESTUDOS (
        id,
        opcional,
        resposta_esperada,
        criterio_id,
        estudo_id
    )
VALUES (
        '018ed483-29b0-70d6-bc06-ff52900a5a0f',
        false,
        'SIM',
        'd6444e7f-d61a-4940-93c1-41c9c04f2a0e',
        '447b1927-5b88-4402-8704-457eedddd163'
    ),
    (
        'a7953838-d107-4a72-a05f-e60df43c6c87',
        true,
        'SIM',
        '018ed4d3-07a3-7895-b6ce-3276eb60d78e',
        '447b1927-5b88-4402-8704-457eedddd163'
    ),
    (
        'dc9c7b63-5b19-4e7d-9ce7-52c19b3758eb',
        true,
        'SIM',
        'f307495a-bf09-4926-a779-1ee434a411f9',
        '447b1927-5b88-4402-8704-457eedddd163'
    ),
    (
        '97670c5f-0e37-4a62-af2f-5b38db4cebc0',
        true,
        'NAO',
        '52906f16-6408-441b-9480-5ce88bed7e23',
        '447b1927-5b88-4402-8704-457eedddd163'
    );
--ESTUDOS FIM--
--MEDICOS INICIO--
INSERT INTO MEDICOS (id, crm, area_id)
VALUES (
        '1e82123d-3c4d-4033-a924-230c6ea9e82d',
        '1234/RS',
        'd6444e7f-d61a-4940-93c1-41c9c04f2a0d'
    );
--MEDICOS FIM--
--PACIENTES INICIO--
--João Rosa
INSERT INTO PACIENTES(
        ID,
        CEP,
        APELIDO,
        DATA_NASCIMENTO,
        SEXO,
        MEDICO_ID
    )
VALUES (
        '641796b8-1c33-4b80-9ef4-53f189e979f8',
        '37529511',
        'João Rosa',
        '1984-01-03',
        'M',
        '1e82123d-3c4d-4033-a924-230c6ea9e82d'
    );
--João Rosa/respostas
INSERT INTO RESPOSTAS (ID, RESPOSTA, CRITERIO_ID, PACIENTE_ID)
VALUES (
        '91d57889-1a0a-4242-8b15-af2742f8e338',
        'NAO',
        '1e90e7ca-7b0a-4693-99cf-a0743fb5132a',
        '641796b8-1c33-4b80-9ef4-53f189e979f8'
    );
--Wando Da Silva
INSERT INTO PACIENTES(
        ID,
        CEP,
        APELIDO,
        DATA_NASCIMENTO,
        SEXO,
        MEDICO_ID
    )
VALUES (
        '1896428f-1aaa-4b1f-a9c9-1398da088a52',
        '01075527',
        'Wando Da Silva',
        '1994-05-13',
        'M',
        '1e82123d-3c4d-4033-a924-230c6ea9e82d'
    );
--Wando Da Silva/respostas
INSERT INTO RESPOSTAS (ID, RESPOSTA, CRITERIO_ID, PACIENTE_ID)
VALUES (
        'dad15f70-712d-48d3-855f-0acf07261f25',
        'SIM',
        '9d4bf6c3-0520-4cfe-86b3-82f0ee13f2aa',
        '1896428f-1aaa-4b1f-a9c9-1398da088a52'
    );
--Elise Guimarões
INSERT INTO PACIENTES(
        ID,
        CEP,
        APELIDO,
        DATA_NASCIMENTO,
        SEXO,
        MEDICO_ID
    )
VALUES (
        '45b870cc-8713-47d6-b9a7-610c746a577e',
        '12082317072',
        'Elise Guimarões',
        '1962-09-25',
        'F',
        '1e82123d-3c4d-4033-a924-230c6ea9e82d'
    );
--Elise Guimarões/respostas
INSERT INTO RESPOSTAS (ID, RESPOSTA, CRITERIO_ID, PACIENTE_ID)
VALUES (
        'b06f30ed-5b4d-48c0-8a59-9b80c522c412',
        'SIM',
        'd6444e7f-d61a-4940-93c1-41c9c04f2a0e',
        '45b870cc-8713-47d6-b9a7-610c746a577e'
    ),
    (
        '1acfae56-80f0-4f0f-b5bd-10599c570204',
        'SIM',
        '1e90e7ca-7b0a-4693-99cf-a0743fb5132a',
        '45b870cc-8713-47d6-b9a7-610c746a577e'
    ),
    (
        '840df6d1-7e41-48bb-8e89-7c2dab40e07a',
        'SIM',
        '018ed4d3-07a3-7895-b6ce-3276eb60d78e',
        '45b870cc-8713-47d6-b9a7-610c746a577e'
    ),
    (
        'f131fc78-960f-4636-a629-ae19a7f3ca6e',
        'NAO',
        '9d4bf6c3-0520-4cfe-86b3-82f0ee13f2aa',
        '45b870cc-8713-47d6-b9a7-610c746a577e'
    );
--PACIENTES FIM--
--PACIENTES ESTUDOS INICIO--
INSERT into PACIENTES_ESTUDOS(PACIENTE_ID, estudo_id)
VALUES(
        '45b870cc-8713-47d6-b9a7-610c746a577e',
        '447b1927-5b88-4402-8704-457eedddd163'
    );