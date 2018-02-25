import { TestBed, inject } from '@angular/core/testing';

import { ServiceConsumer } from './service-consumer.service';

describe('ServiceConsumerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ServiceConsumer]
    });
  });

  it('should be created', inject([ServiceConsumer], (service: ServiceConsumer) => {
    expect(service).toBeTruthy();
  }));
});
