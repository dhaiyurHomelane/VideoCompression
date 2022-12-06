import { WebPlugin } from '@capacitor/core';

import type { VideoCompressionPlugin } from './definitions';

export class VideoCompressionWeb
  extends WebPlugin
  implements VideoCompressionPlugin
{
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
